package edu.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFileUtil {
	/**
	 * 
	 * 服务端保存的上传文件的最大限制
	 *
	 */
	private static int UploadMaxFileSize = 1024 * 1024 * 5;
	/**
	 * 
	 * 服务端保存的上传文件的根目录
	 * 
	 */
	private static String UploadRootName = "upload";

	/**
	 * 处理服务端保存上传文件的根目录
	 * 
	 */
	private static String dealRootDir(HttpServletRequest request) {
		// 上传文件的的存储路径(服务器文件系统上的绝对文件路径)
		String uploadFilePath = request.getServletContext().getRealPath(UploadRootName);
		File vRootDir = new File(uploadFilePath);
		// 判断上传文件的保存目录是否存在
		if (!vRootDir.exists() && !vRootDir.isDirectory()) {
			// System.out.println(uploadFilePath+"目录不存在,需要创建");
			// 创建目录
			vRootDir.mkdir();
		}
		return uploadFilePath;
	}

	/**
	 * 上传文件的拓展名列表
	 * 
	 */
	private static List<String> fileExtList = Arrays.asList("png", "gif", "jpg", "jpeg");

	/**
	 * 获得上传文件的拓展名列表
	 * 
	 * 
	 */
	public static List<String> getFileExtList() {

		return fileExtList;
	}

	public static UploadFileResult uploadFile(HttpServletRequest request) {
		UploadFileResult result = new UploadFileResult();
		String uploadFilePath = dealRootDir(request);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(UploadMaxFileSize);//设置上传文件的最大限制
			//解析for表单中所有文件
			try {
				//上传的Servlet,分析请求对象,解析成列表对象List<FileItem>
				List<FileItem> items = upload.parseRequest(request);
				File saveFile = null;//上传并保存的文件
				boolean isUnallowedType = false;//是否为不允许的类型
				for(FileItem item : items) {
					isUnallowedType = false;
					if(item.isFormField()) {//判断为普通表单字段
						
					//fieldName = item.getFieldName();
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					//将parameter的内容放到attribute里
					request.setAttribute(name, value);
					}else {
						//文件表单字段
						String fileName = item.getName();
						if(fileName.length() > 0) {
							int index = fileName.lastIndexOf(".");//查找.字符的下标
							//取得拓展名
							String extName = index == -1 ? "":fileName.substring(index+1).toLowerCase();
							if(getFileExtList().contains(extName)) {//判断文件拓展名类型是否在允许范围内
								File fullFile = new File(item.getName());
								String oldName = fullFile.getName();//原名称
								String newName = oldName;//新名称,一开始显示旧名称
							
								//使用UUID来随机生成一个唯一的名称(去掉-)
								String uuid = UUID.randomUUID().toString().replace("-", "");
								newName = uuid;//使用生成的uuid来作为新的文件夹名字,防止重名
								newName = newName +"." + extName;
								saveFile = new File(uploadFilePath,newName);
								item.write(saveFile);
								//news.setNpicpath(uploadFilePath+File.pathSeparator+fullFile.getName())
								//上传成功
								result.setCode(0L);
								result.setDesc(String.format("/%s/%s", UploadRootName,newName));
							}else {
								isUnallowedType = true;//拓展名对应的文件不允许上传
							}
						}
					}
					if(isUnallowedType) {
						result.setCode(-2L);
						result.setDesc("图片上传失败,文件类型只能是("+getFileExtList().toString()+")。");
					}
				}
				
			} catch (FileUploadBase.SizeLimitExceededException e) {
				// TODO Auto-generated catch block
				result.setCode(-99L);
				result.setDesc("图片上传失败,文件的最大限制是:"+1.0*UploadMaxFileSize/1024+"KB");
				
			}catch (Exception ex) {
				result.setCode(-99L);
				result.setDesc("图片上传失败,文件的最大限制是:"+ex.getMessage()+".");
			}
		}
		return result;
	}
}
