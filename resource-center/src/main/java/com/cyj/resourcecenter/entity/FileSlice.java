package com.cyj.resourcecenter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 文件分片上传实体类
 * 注意:文件名称,文件总大小,文件在磁盘的最后修改时间,三个条件基本可以作为文件的唯一识别
 * @author MaShuai
 *
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
public class FileSlice {
	
	
	private String fid;//文件唯一标志
	private Long point;//文件断点位置,字节位置
	//slice=1M = 1024 * 1024;js的数组取arr.slice(0,4)
	private Long sliceSize;//文件每片大小,单位字节;1M = 1024 * 1024 字节,对应js字节数组中的方法arr.slice(2,4) :从第2取到第4,包含2不包含4
	private Long index;//当前第几片,片的索引从0开始
	private String name;//文件名称
	private Long totalSize;//文件总大小,单位字节;对应js中的 file.size 得到文件字节的大小. 1M = 1024 * 1024 字节
	private Long totalSlice;//总片数
	private Long lastModified;// JS文件对象的属性,可返回文档最后被修改的日期和时间
	
	@Override
	public String toString() {
		return "FileSlice [fid=" + fid + ", point=" + point + ", sliceSize=" + sliceSize + ", index=" + index
				+ ", name=" + name + ", totalSize=" + totalSize + ", totalSlice=" + totalSlice + ", lastModified="
				+ lastModified + "]";
	}
	
	
	

}
