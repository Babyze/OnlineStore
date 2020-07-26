/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.utils;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author babyz
 */
public class FileHelper implements Serializable {
    
    public static Map<String, String> getResource(String realPath, String baseName) throws MalformedURLException {
        Map<String, String> map = new HashMap<>();
        File file = new File(realPath);
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.getDefault(), loader);
        Enumeration<String> labels = resourceBundle.getKeys();
        while (labels.hasMoreElements()) {
            String label = labels.nextElement();
            String resource = resourceBundle.getString(label);
            map.put(label, resource);
        }
        return map;
    }
    
}
