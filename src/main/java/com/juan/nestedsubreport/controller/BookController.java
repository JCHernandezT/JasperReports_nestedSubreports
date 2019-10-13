package com.juan.nestedsubreport.controller;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.juan.nestedsubreport.model.Book;
import com.juan.nestedsubreport.model.Page;
import com.juan.nestedsubreport.service.AuthorService;
import com.juan.nestedsubreport.service.BookService;
import com.juan.nestedsubreport.service.PageService;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PageService pageService;

	@Autowired
	private JasperReportsViewResolver jasperReportsViewResolver;

	@GetMapping(value = "/generatePDF")
	public String generatePDF(HttpServletRequest request, Model model) {

		jasperReportsViewResolver.clearCache();
		final Properties headers = new Properties();
		String filename = "mainReport.pdf";
		headers.setProperty("Content-Disposition", "attachment; filename=" + filename);
		jasperReportsViewResolver.setHeaders(headers);
		model.addAttribute("format", "pdf");

		List<Book> books = bookService.findAll();
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(books);
		model.addAttribute("datasource", jrDataSource);

		JRBeanCollectionDataSource dataSourceSbr = new JRBeanCollectionDataSource(authorService.findAllAuthors());
		model.addAttribute("dataSourceSbr", dataSourceSbr);

		List<Page> pages = pageService.findAll();
		// List<Page> pages = new ArrayList<Page>();
		JRBeanCollectionDataSource dataSourceSUBsbr = new JRBeanCollectionDataSource(pages);
		model.addAttribute("dataSourceSUBsbr", dataSourceSUBsbr);

		Properties subReportUrls = new Properties();
		subReportUrls.setProperty("subreport", "classpath:/jasperreports/nest-subreport.jrxml");
		subReportUrls.setProperty("subsubreport", "classpath:/jasperreports/nest-sub-subreport.jrxml");
		jasperReportsViewResolver.setSubReportUrls(subReportUrls);

		return "nest-master";
	}

}
