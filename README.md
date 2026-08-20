# 親保存で新規子をカスケード永続化しないデバッグラボ

## はじめに

親へ新規子を追加して保存しても、子の永続化は自動ではありません。`CascadeType.PERSIST`は永続化操作を関連エンティティへ伝播します。[1]

## この題材で守る契約

`Catalog`へ追加した新規`CatalogItem`は、親の保存後に再読込した親から一件取得できなければなりません。

## 最短の開始手順

```bash
mvn --batch-mode clean test
```

## バグを再現する

```bash
git switch --detach 879d8c4
mvn --batch-mode test -Dtest=CatalogRepositoryTest
git switch main
```

バグ状態では親は保存されますが、子は再読込したコレクションに現れません。出力は`evidence/01-bug-service-test-output.txt`にあります。

## 最小修正

```java
@OneToMany(mappedBy = "catalog", cascade = CascadeType.PERSIST)
private List<CatalogItem> items = new ArrayList<>();
```

修正コミットは`64ae09e`です。削除伝播や全操作のカスケードは指定しません。

## References

[1]: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/cascadetype "Jakarta Persistence API: CascadeType"
