package com.music.file;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.music.model.MusicInfo;
/**
 *
 * @author Huang Ruixin
 */
public interface FactoryFileMp3Server {

	
	 public  List<MusicInfo> getMusicInfoList(String relativePath) throws IOException;
	 
	 
	 public  List<String> getControllerClasses() throws IOException;
	 
	 
	 public  String getJarPath();
	 
	 
	 public  boolean isJar();
	 
	    /**
	     * 
	     *
	     * @param relativePath 
	     * @return {@link MusicInfo}
	     */
	    default List<MusicInfo> getMusicInfoFromLocal(String relativePath) {
	        List<MusicInfo> musicList = new ArrayList<>();
	        File files = new File(FactoryFileMp3ServerImpl.class.getResource(relativePath).getPath());
	        for (File file : files.listFiles()) {
	            String musicName = file.getName();
	            String musicPath = file.getAbsolutePath();
	            musicPath = musicPath.replaceAll("\\\\", "/");
	            musicPath = musicPath.substring(musicPath.indexOf("/music"));

	            MusicInfo musicInfo = new MusicInfo(musicName, musicPath);
	            musicList.add(musicInfo);
	        }
	        return musicList;
	    }

	    /**
	     *  
	     * @param relativePath 
	     * @return
	     * @throws IOException
	     */
	     default List<MusicInfo> getMusicInfoFromJar(String relativePath) throws IOException {
	        List<MusicInfo> musicList = new ArrayList<>();

	        Enumeration<JarEntry> entries = getJarEntries();
	        while (entries.hasMoreElements()) {
	            JarEntry entry = entries.nextElement();
	            String innerPath = entry.getName();

	            if (innerPath.startsWith("music/") && !innerPath.endsWith("music/")) {
	                String musicName = innerPath.substring(innerPath.indexOf("/") + 1);
	                String musicPath = "/music/" + URLEncoder.encode(musicName, "UTF-8");
	                MusicInfo musicInfo = new MusicInfo(musicName, musicPath);
	                musicList.add(musicInfo);
	            }
	        }
	        return musicList;
	    }
	    
	     default Enumeration<JarEntry> getJarEntries() throws IOException {
	        String jarPath = getJarPath();
	        File file = new File(jarPath);
	        JarFile jarFile = new JarFile(file);
	        return jarFile.entries();
	    }
}
