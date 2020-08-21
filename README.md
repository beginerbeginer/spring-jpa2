# spring-jpa2

## 情報1

- リポジトリ（JpaRepository）では、いくつかの便利な機能が使えます。<br>ここで使用したのは、以下の２つです。

  - repository.findAll()・・・データを全件取得します。 <br>
  - repository.save(object)・・・データを保存します。
  
  
 ## 情報2
 
 DataLoader.javaを、以下のように編集します。 
 

 ```java:src/main/java/com.example.demo.config.DataLoader.java 
 
 package com.example.demo.config; 
 import org.springframework.boot.CommandLineRunner; 
 import org.springframework.stereotype.Component;  
 import com.example.demo.model.Comment; 
 import com.example.demo.repository.CommentRepository;  
 import lombok.RequiredArgsConstructor;  
 
 @RequiredArgsConstructor 
 @Component 
 public class DataLoader implements CommandLineRunner {
    private final CommentRepository repository;      
    @Override     
    public void run(String... args) throws Exception {
        Comment comment = new Comment();
        comment.setContent("こんにちは");
        repository.save(comment);
        
        comment = new Comment();
        comment.setContent("テストコメント");
        repository.save(comment);     
     } 
 }

```
 
 
 
 - @RequiredArgsConstructor・・・コンストラクタを自動生成します。
   - 引数は、finalなフィールドです。<br> Lombokの「@RequiredArgsConstructor」を使うと、コンストラクタ インジェクションを省略できます。
   - これは、以下のコードを実装したのと同じになります。
   
   
   ```java
   
   @Component 
   public class DataLoader implements CommandLineRunner {
      private final CommentRepository repository;
      
      // @RequiredArgsConstructorが自動生成するコード
      public DataLoader(CommentRepository repository) {
        this.repository = repository;
      }
   
   ```
   
- ※これを使うと、前のCommentControllerクラスも、コンストラクタ インジェクションを省略できます。
#### CommandLineRunner
- これを実装したクラスを作成すると、コマンドラインで実行するアプリケーションを作成できます。
- 処理内容はrunメソッドに記載します。 

#### @Component
- このクラスがコンポーネントであることを示します。コンポーネントは「構成要素」「部品」という意味です。   
