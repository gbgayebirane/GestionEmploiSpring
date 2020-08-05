package com.gb.demo.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.demo.dao.CvRepo;
import com.gb.demo.dao.Upload;
import com.gb.demo.dao.UserRepo;
import com.gb.demo.entities.Cv;
import com.gb.demo.entities.Offre;
import com.gb.demo.entities.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@Controller
@MultipartConfig(maxFileSize = 16177215)
public class CvController {
	@Autowired
	private UserRepo userdao;
	@Autowired
	private CvRepo icv;
	 private static final String chemin ="D:\\Master1\\jee\\GestionEmploiSpring\\src\\main\\resources\\static\\fichiers\\";
	@RequestMapping(value="/Cv/liste")
	 public String liste(ModelMap model)
   {
	
		
       return "cv/liste";
   }
	@RequestMapping(value="/Cv/import")
	 public String importer(ModelMap model,HttpServletRequest request) throws IOException, ServletException
  {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User u=userdao.findUserByStatusAndName(auth.getName());
		Cv c=new Cv();
		Part p = null;
        
      
            p = request.getPart("fichier");
            String filePath = chemin + p.getSubmittedFileName();
            System.out.println(filePath);
            InputStream stream = p.getInputStream();
       
            Upload.saveFile(stream, filePath);
            System.out.println("yeah");
        

		c.setFichier(p.getSubmittedFileName());
		c.setUser(u);
		  System.out.println(p.getSubmittedFileName());
		 
			
			 if(icv.moncv(auth.getName())!=null)
			 {
				 
			 }else {
		      icv.save(c);
			 }
      return "cv/liste";
  }
	@RequestMapping(value="/Cv/visualiser")
	 public String publier(ModelMap modelmap) throws DocumentException, IOException, InterruptedException
	    {
		PdfReader reader;
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		 String moncv=icv.moncv(auth.getName());
		 System.out.println(moncv);
        try {
        	
       	// final String FILE__NAME = "/GestionEmploiSpring/src/main/resources/static/fichiers/CV.pdf";
        	final String FILE__NAME ="D:\\\\Master1\\\\jee\\\\GestionEmploiSpring\\\\src\\\\main\\\\resources\\\\static\\\\fichiers\\\\"+moncv;
       	 //if ((new File("c:\\Java-Interview.pdf")).exists()) {
       	 Process p = Runtime
                 .getRuntime()
                 .exec("rundll32 url.dll,FileProtocolHandler "+FILE__NAME);
              p.waitFor();
              
              return  "redirect:/Cv/liste";
		
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "cv/visualiser";
		

		
	    }
	
	@RequestMapping(value="/Cv/creer")
	 public void creercv(ModelMap model) throws InterruptedException
  {
		Document document = new Document();

        try {
        	 final String FILE__NAME = "C:\\Users\\FOLIO\\Desktop\\cpi\\itext.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE__NAME)));

           //open
            document.open();

            Paragraph p = new Paragraph();
            p.add("This is my paragraph 1");

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add("This is my paragraph 2");//no alignment

            document.add(p2);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);

            document.add(new Paragraph("This is my paragraph 3", f));

           //close
            document.close();

            System.out.println("Done");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
     // return "cv/creer";
  }
	
	
}
