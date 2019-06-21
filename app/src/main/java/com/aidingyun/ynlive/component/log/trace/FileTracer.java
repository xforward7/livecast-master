package com.aidingyun.ynlive.component.log.trace;

import android.content.Context;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author ianmao 2014-6-12
 */
public class FileTracer extends Tracer {
    private Context mContext;

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private OutputStreamWriter fileWriter; // �ļ���д����ʹ��FileWriter����BufferedWriter��ԭ���Ǳ����Ѿ��Ƕ�������I/O������
    private File currTraceFile;

    private FileFilter traceFilter = new FileFilter() // ɨ����־��Ƭ�ļ��Ĺ�����
    {

        @Override
        public boolean accept(File pathname) {
            String fileName = pathname.getName();

            // �����չ���Ƿ��������
            boolean conditionA = fileName.endsWith(TracerConfig.DEF_TRACE_FILEEXT)
                    && fileName.startsWith(TracerConfig.getLogFilePre(mContext));

            if (!conditionA) {
                return false;
            }

            // ����ļ����Ƿ�Ϊ���
            boolean conditionB = getBlockCountFromFile(pathname) != -1;

            return conditionB;
        }
    };

    public FileTracer(Context context) {
        super();
        mContext = context;

        obtainFileWriter(); // ��ʼ���ļ���������
    }

    /**
     * ��ȡ�ļ���д����
     *
     * @return Writerʵ��
     */
    private Writer obtainFileWriter() {
        boolean forceChanged = false;

        // �������л������Ӧ��д����ļ�
        File newFile = getCurrFile();

        // �ļ��������˾�ǿ�Ƹ���
        if (currTraceFile != null) {
            if (!currTraceFile.exists() || !currTraceFile.canWrite()) {
                forceChanged = true;
            }
        }

        // ����ǲ�ͬ��֮ǰ���ļ�����رյ�ǰ�ļ���д�������´���
        if (forceChanged || ((newFile != null) && (!newFile.equals(currTraceFile)))) {
            currTraceFile = newFile;

            closeFileWriter();

            try {
                FileOutputStream fos = new FileOutputStream(currTraceFile, true);
                fileWriter = new OutputStreamWriter(fos);
            } catch (IOException e) {
                return null;
            }
        }

        return fileWriter;
    }

    /**
     * �ر��ļ�д����
     */
    private void closeFileWriter() {
        try {
            if (fileWriter != null) {
                fileWriter.flush();
                fileWriter.close();
            }
        }
        // FileWriter��Ϊʹ����CharsetEncoder��
        // ���Ի��׳�IllegalStateException��
        // ��ʹ����Ҫ�����򶨣���ץ�쳣=_=
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void flush(String content) {
        try {
            Writer fos = obtainFileWriter();

            if (fos != null) {
                fos.write(content);
                fos.flush();
            }
        } catch (Exception e) {

        } finally {
        }
    }

    /**
     * ��õ�ǰӦ���������־�ļ�
     *
     * @return ��־�ļ�·��
     */
    public File getCurrFile() {
        return getWorkFile(System.currentTimeMillis());
    }

    /**
     * ���ָ��ʱ���������־��Ƭ��������Ƭ״��
     *
     * @param time ʱ��
     * @return ��ʱ���������־��Ƭ
     */
    private File getWorkFile(long time) {
        File folder = getWorkFolder(time);

        return ensureBlockCount(folder);
    }

    /**
     * ���ָ��ʱ�����־Ŀ¼������������򴴽���Ŀ¼
     *
     * @param time ʱ��
     * @return ָ��ʱ�����־Ŀ¼
     */
    public File getWorkFolder(long time) {

        File workFolder = getWorkFolderPath(time);
        if (!workFolder.exists())
            workFolder.mkdirs();

        return workFolder;
    }

    private File getWorkFolderPath(long time) {
        File workFolder = new File(TracerConfig.getLogDir(mContext), mDateFormat.format(time));
        return workFolder;
    }

    /**
     * �õ���ǰӦ��ʹ�õķ�Ƭ�ļ�<br>
     * <br>
     * �⽫����Ƭ�����������Ƭ�ķ�Ƭ��С�������������Ƭ������ɾ���ɷ�Ƭֱ������Ҫ��������µķ�Ƭ������Ƭ��С���ƣ��򴴽��µķ�Ƭ
     *
     * @param folder ��Ƭ��ŵ��ļ���
     * @return ��ǰӦ��ʹ�õķ�Ƭ�ļ�
     */
    private File ensureBlockCount(File folder) {
        // ö�����з�Ƭ
        File[] files = getAllBlocksInFolder(folder);

        // �����ǰû�з�Ƭ���򷵻��·�Ƭ
        if (files == null || files.length == 0) {
            return new File(folder, TracerConfig.getLogFilePre(mContext) + "1" + TracerConfig.DEF_TRACE_FILEEXT);
        }
        // �����оɷ�Ƭ������������
        sortBlocksByIndex(files);
        // ȡ���������µķ�Ƭ
        File resu = files[files.length - 1];
        // ������Ҫ����ľɷ�Ƭ����
        int cleanCount = files.length - TracerConfig.MAX_BLOCK_COUNT;
        // ������·�Ƭ�Ƿ񳬹��˷�Ƭ��С����
        if ((int) resu.length() > TracerConfig.MAX_BLOCK_SIZE) {
            // ������ʹ���µķ�Ƭ
            int newIndex = getBlockCountFromFile(resu) + 1;
            resu = new File(folder, TracerConfig.getLogFilePre(mContext) + newIndex + TracerConfig.DEF_TRACE_FILEEXT);
            // ��Ϊ�������µķ�Ƭ��������Ҫɾ���ľɷ�Ƭ�ֶ���һ��
            cleanCount += 1;
        }

        // ɾ�����оɷ�Ƭ
        for (int i = 0; i < cleanCount; i++) {
            files[i].delete();
        }

        // �������µķ�Ƭ
        return resu;
    }

    /**
     * ö����־Ŀ¼�µ����з�Ƭ
     *
     * @param folder ��־Ŀ¼
     * @return ��Ƭ�ļ�����
     */
    public File[] getAllBlocksInFolder(File folder) {
        return folder.listFiles(traceFilter);
    }

    /**
     * ���մӾɵ��µ�����˳�����з�Ƭ
     *
     * @param blockFiles ��Ƭ�ļ�����
     * @return �������ļ�����
     */
    public File[] sortBlocksByIndex(File[] blockFiles) {
        Arrays.sort(blockFiles, blockComparetor);

        return blockFiles;
    }

    private Comparator<? super File> blockComparetor = new Comparator<File>() // ��Ƭ�ļ�������
    {

        @Override
        public int compare(File lhs, File rhs) {
            return getBlockCountFromFile(lhs) - getBlockCountFromFile(rhs);
        }
    };

    /**
     * ����ļ��ķ�Ƭ����
     *
     * @param file �ļ�
     * @return ��Ƭ������������Ƿ�Ƭ�ļ����򷵻�-1
     */
    private int getBlockCountFromFile(File file) {
        try {
            String fileName = file.getName();

            String pre = TracerConfig.getLogFilePre(mContext);

            int p0 = pre.length();

            int p = fileName.indexOf('.', p0 + 1);

            fileName = fileName.substring(p0, p);

            return Integer.parseInt(fileName);
        } catch (Exception e) {
            // �ļ�Ϊ�ա��ļ������Ϸ�
            return -1;
        }
    }
}
