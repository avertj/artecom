/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.ByteArrayInputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author donatien
 */
@ManagedBean(name = "uploadImgBean")
@SessionScoped
public class UploadImgBean {

    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public StreamedContent getUploadedFileAsStream() {
        if (uploadedFile != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(uploadedFile.getContents()));
        }
        return null;
    }

    public void uploadFile(FileUploadEvent event) {
        uploadedFile = event.getFile();
    }
}
