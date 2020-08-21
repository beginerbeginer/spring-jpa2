package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Controller
public class CommentController {
	/* JpaRepository<Comment, Long>を継承しているため、DBの取得や保存など、便利な機能が使える */
	private final CommentRepository repository;

	/* @Autowired ← コンストラクタが１つの場合、@Autowiredは省略できる */
	public CommentController(CommentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public String getAllComments(@ModelAttribute Comment comment, Model model) {
		model.addAttribute("comments", repository.findAll());
		return "list";
	}

	@PostMapping("/add")
	/*
	 * @Validated・・・入力値のチェックを行います。
	 * チェックの結果は、BindingResultに入るので、result.hasErrors()でエラーがあるか確認できます。
	 */
	public String addComment(@Validated @ModelAttribute Comment comment, BindingResult result, Model model) {
		/* model.addAttribute("comment", comment); ←この場合なら省略できた。今回は消え行きが違うため不可。 */
		model.addAttribute("comments", repository.findAll()); /* 全てのレコードをcommentsに渡す。 */
		/* バリデーションの処理 */
		if (result.hasErrors()) {
			return "list";
		}
		repository.save(comment);

		// ルートパス("/")に、リダイレクト
		return "redirect:/";
	}
}
