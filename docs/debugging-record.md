# デバッグ記録: 親保存で新規子をカスケード永続化しない

## 実行環境と再現境界

Java 21、Spring Boot 3.4.3、Spring Data JPA、Hibernate、H2を使い、親保存後に別トランザクションで子コレクションを再読込します。

## 再現手順

`879d8c4`で`mvn --batch-mode test -Dtest=CatalogRepositoryTest`を実行すると失敗します。`main`で`mvn --batch-mode clean test`を実行すると成功します。

## 最初に観測した事実

親保存は例外なく完了しましたが、再読込した親の子件数は0でした。証跡は`evidence/01-bug-service-test-output.txt`です。

## 競合仮説と検証

| 仮説 | 検証 | 結果 |
| --- | --- | --- |
| 所有側未設定 | 子の`catalog`を設定済みにする | 設定済みで棄却 |
| 子永続化が伝播しない | PERSISTだけを追加する | 子件数1となり採用 |

## 確定した原因

関連に`CascadeType.PERSIST`がなく、親への永続化操作が新規子へ伝播しません。[1]

## 最小修正

`@OneToMany`へ`cascade = CascadeType.PERSIST`を追加しました。

## 回帰保証

### 再発防止テスト

`CatalogRepositoryTest`は親保存後に別トランザクションで子件数を確認します。成功出力は`evidence/03-fixed-full-test-output.txt`です。

## スコープと注意点

新規子のPERSIST伝播だけを扱います。MERGE、REMOVE、orphanRemoval、既存データ移行は対象外です。

## References

[1]: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/cascadetype "Jakarta Persistence API: CascadeType"
