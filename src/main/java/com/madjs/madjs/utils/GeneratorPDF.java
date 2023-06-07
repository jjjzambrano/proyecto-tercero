package com.madjs.madjs.utils;

 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class GeneratorPDF {

    @Autowired
	private TemplateEngine templateEngine;
	public String createPdf(String templateName, Map map, String reportName) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		String fullPath="";
		Context ctx = new Context();
		if (map != null) {
		     Iterator itMap = map.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry pair = (Map.Entry) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		String processedHtml = templateEngine.process(templateName, ctx);
		  FileOutputStream os = null;
	        try {
	            final File outputFile = File.createTempFile(reportName, ".pdf");
	            os = new FileOutputStream(outputFile);
				fullPath = outputFile.getAbsolutePath();
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
			return fullPath;
	}
}
