Author: Eunmin Kim
Date: 2016년 1월 30일 토요일
Title: Readme
Subtitle: Simple blog for markdown

# mill

Simple blog for markdown

## Metadata (MultiMarkdown Spec)

Required metadata (https://github.com/fletcher/MultiMarkdown/wiki/MultiMarkdown-Syntax-Guide#metadata)

```
Title: ...
Author: ...
Date: ...
```

## Usage

```bash
lein uberjar
java -cp target/mill.jar -DBASE_PATH=articles -DPORT=8080 mill.main
```

### Options

- BASE_PATH : markdown file directory path
- PORT : server port
- CONFIG : edn format config file path 

### Sample configuration (default config)

```edn
{:title  "Clean Blog"
 :description "A Clean Blog Theme by Start Bootstrap"
 :logo "Start Bootstrap"
 :copyright "Copyright © Your Website 2016"
 :article-count-per-page 5}
```

## License

Copyright © Eunmin Kim 2016

