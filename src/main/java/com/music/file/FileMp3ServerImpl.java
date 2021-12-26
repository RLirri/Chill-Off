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

public class FileMp3ServerImpl implements FileMp3Server {


    public  List<MusicInfo> getMusicInfoList(String relativePath) throws IOException {
        if (isJar()) {
            return getMusicInfoFromJar(relativePath);
        }else {
            return getMusicInfoFromLocal(relativePath);
        }
    }

   

    /**
     * {@link com.music.MusicScreen} 
     *
     * @return
     * @throws IOException
     */
    public  List<String> getControllerClasses() throws IOException {
        List<String> list = new ArrayList<>();

        Enumeration<JarEntry> entries = getJarEntries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String innerPath = entry.getName();
            if (innerPath.startsWith("com/jratil/controller") && innerPath.endsWith("Controller.class")) {
                innerPath = innerPath.replaceAll("/", ".").replaceAll("\\\\", ".").replace(".class", "");
                list.add(innerPath);
            }
        }
        return list;
    }


    /**
     * 
     * 
     * @return
     */
    public  String getJarPath() {
        return FileMp3ServerImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    /**
     *
     * 
     * @return
     */
    public  boolean isJar() {
        return !(new File(getJarPath()).isDirectory());
    }
}
