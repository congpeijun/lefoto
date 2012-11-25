package com.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * ʹ��URLConnection�����ļ���ͼƬ�����浽���ء�
 * 
 * @author ������(laozizhu.com)
 */
public class DownloadPic {
//	static String fileName1 = "D:/imgGrab/imgUrls/woxihuanTongQu.txt";
//	static String filePath1 = "D:/imgGrab/woxihuanTongQu/";

	public static void main(String[] args) throws Exception {

		String[] fileNames = 
			{"D:/imgGrab/imgUrls/woxihuanTongQu.txt",
			 "D:/imgGrab/imgUrls/woxihuanMeiShi.txt",
			 "D:/imgGrab/imgUrls/woxihuanMeiNv.txt",
			 "D:/imgGrab/imgUrls/woxihuanGaoXiao.txt",
			 "D:/imgGrab/imgUrls/woxihuanDongMan.txt",
			 "D:/imgGrab/imgUrls/duitangMengTu.txt",
			 "D:/imgGrab/imgUrls/duitangDongMan.txt"};
		String[] filePaths = 
			{"D:/imgGrab/woxihuantongqu/",
			 "D:/imgGrab/woxihuanmeishi/",
			 "D:/imgGrab/woxihuanmeinv/",
			 "D:/imgGrab/woxihuangaoxiao/",
			 "D:/imgGrab/woxihuandongman/",
			 "D:/imgGrab/duitangmengtu/",
			 "D:/imgGrab/duitangdongman/"};
		for(int index = 0; index < 7; index++){
			String fileName = fileNames[index];
			String filePath = filePaths[index];
			File file = new File(fileName);
			BufferedReader reader = null;
			try {
				System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
				reader = new BufferedReader(new FileReader(file));
				String imgUrl = null;
				int line = 1;
				// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
				while ((imgUrl = reader.readLine()) != null) {
					// ��ʾ�к�
					String imgName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
					System.out.println(imgName);
					try {

						download(imgUrl, filePath + imgName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					line++;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		}
		System.out.println("Download Finish!��");
	}

	/**
	 * �����ļ�������
	 * 
	 * @param urlString
	 *            �����ص��ļ���ַ
	 * @param filename
	 *            �����ļ���
	 * @throws Exception
	 *             �����쳣
	 */
	public static void download(String urlString, String filename)
			throws Exception {
		// ����URL
		URL url = new URL(urlString);
		// ������
		URLConnection con = url.openConnection();
		// ������
		InputStream is = con.getInputStream();
		// 1K�����ݻ���
		byte[] bs = new byte[1024];
		// ��ȡ�������ݳ���
		int len;
		// ������ļ���
		OutputStream os = new FileOutputStream(filename);
		// ��ʼ��ȡ
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// ��ϣ��ر���������
		os.close();
		is.close();
	}

	/**
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
}
