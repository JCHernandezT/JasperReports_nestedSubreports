package com.juan.nestedsubreport.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juan.nestedsubreport.model.Author;
import com.juan.nestedsubreport.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

	private static Logger logger = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@GetMapping("/list")
	public String getAuthorList(Model model) {
		logger.debug("getAuthorList");
		List<Author> authorEntities = authorService.findAllAuthors();
		authorEntities.forEach(author -> author.setPercentaje(author.getAge() * 0.15d));
		model.addAttribute("authorEntities", authorEntities);
		logger.debug("getAuthorList model=[{}]", model.asMap());
		return "author/list";
	}

	@GetMapping("/new")
	public String getNewAuthor(Model model) {
		logger.debug("getNewAuthor");
		Author authorEntity = new Author();
		model.addAttribute("title", "New Author");
		model.addAttribute("authorEntity", authorEntity);
		logger.debug("getNewAuthor model = [{}]", model.asMap());
		return "author/addupdate";
	}

	@GetMapping("/edit/{id}")
	public String getEditAuthor(@PathVariable("id") Long id, Model model) {
		logger.debug("getEditAuthor id = [{}]", id);
		Author authorEntity = authorService.findOne(id);
		model.addAttribute("title", "Edit Author");
		model.addAttribute("authorEntity", authorEntity);
		logger.debug("getEditAuthor model = [{}]", model.asMap());
		return "author/addupdate";
	}

	@GetMapping("/delete/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) {
		logger.debug("deleteAuthor id = [{}]", id);
		try {
			authorService.delete(id);
		} catch (DataIntegrityViolationException e) {
			logger.debug("deleteAuthor errorDataIntegrity e=[{}]", e.getMessage());
		} catch (Exception e) {
			logger.debug("deleteAuthor error e=[{}] - ", e.getMessage());
		}
		return "redirect:/author/list";
	}

	@PostMapping("/save")
	public String saveAuthor(@ModelAttribute("authorEntity") @Valid Author authorForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		logger.debug("saveAuthor authorForm = [{}]", authorForm);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("entityWithErrors", authorForm);
			redirectAttributes.addFlashAttribute("validationErrors", bindingResult);
			logger.debug("saveAuthor validationErrors errors=[{}]", bindingResult.getAllErrors());
		} else {
			try {
				authorService.save(authorForm);
			} catch (DataIntegrityViolationException e) {
				logger.debug("saveAuthor errorDataIntegrity authorForm=[{}] - ", authorForm, e);
			} catch (Exception e) {
				logger.debug("saveAuthor error authorForm=[{}] - ", authorForm, e);
			}
		}
		logger.debug("saveAuthor model = [{}]", model.asMap());
		if (authorForm.getId() == 0) {
			return "redirect:/author/new";
		}
		return "redirect:/author/list";
	}

	@GetMapping("/ajax")
	public String seeDynamicAuthor(Model model) {
		logger.debug("seeDynamicAuthor");
		model.addAttribute("title", "Ajax Author");
		return "author/ajax";
	}

	/**
	 * Gets JSON of an author.
	 * 
	 * @param code
	 *            author code
	 * @return an author
	 */
	@GetMapping(value = "/get-author/{code}")
	public @ResponseBody Author getAuthor(@PathVariable("code") String code) {
		logger.debug("getAuthor code=[{}]", code);
		Author author = new Author();
		author = authorService.findByCode(code);
		logger.debug("getAuthor author=[{}]", author);
		return author;
	}

	/**
	 * Gets JSON of all the authors
	 * 
	 * @return all the authors
	 */
	@GetMapping(value = "/get-authors")
	public @ResponseBody List<Author> getAuthors() {
		logger.debug("getAuthors");
		List<Author> authors = authorService.findAllAuthors();
		logger.debug("getAuthors authors=[{}]", authors);
		return authors;
	}

	/**
	 * Resolves the view for search authors.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String searchAuthors(Model model) {
		logger.debug("searchAuthors");
		return "author/search";
	}

	/**
	 * Called with load() from JavaScript
	 * 
	 * @param model
	 * @param code
	 * @return
	 */
	@GetMapping("/search/{code}")
	public String searchResults(Model model, @PathVariable("code") String code, HttpServletResponse response) {
		logger.debug("searchAuthors");
		List<Author> authorEntities = authorService.findAllAuthors();
		List<Author> authors = authorEntities.stream().filter(author -> author.getCode().contains(code))
				.collect(Collectors.toList());
		logger.debug("searchResults authors=[{}]", authors);
		if (authors != null && !authors.isEmpty()) {
			logger.debug("searchResults adding authors");
			model.addAttribute("authorEntities", authors);
		} else {
			logger.debug("searchResults NotFound BadRequest response");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return "fragments/search :: authors";
	}
}
