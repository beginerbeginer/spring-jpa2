package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor・・・コンストラクタを自動生成します。引数は、finalなフィールドです。
//Lombokの「@RequiredArgsConstructor」を使うと、コンストラクタ インジェクションを省略できます。

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

	private final CommentRepository repository;

	@Override
	/*
	 * 可変引数（引数を任意の数だけ記述することができる） (データ型... 変数名)
	 */
	public void run(String... args) throws Exception {
		Comment comment = new Comment();
		comment.setContent("こんにちは");
		repository.save(comment);

		comment = new Comment();
		comment.setContent("テストコメント");
		repository.save(comment);
	}

}
