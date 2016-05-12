package cn.edu.zucc.chenxg.utils;

import java.io.File;

public class DeleteFiles {

	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public boolean deletePNGs(int count) {
		boolean flag = false;

		for (int i = 0; i < count; i++) {

			File file = new File(new Constant().DEFUALT_IMG_PATH + "slides" + "-" + i + ".png");
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
				flag = true;
			}

		}
		return flag;
	}

}
