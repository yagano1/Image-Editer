package editer.minhnhan.in.imageediter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by linh on 6/20/2016.
 */
public class ImageFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        else if (isImageFile(file.getAbsolutePath())) {
            return true;
        }
        return false;
    }
    private boolean isImageFile(String filePath) {
        if (filePath.endsWith(".jpg") || filePath.endsWith(".png"))
        {
            return true;
        }
        return false;
    }
}
