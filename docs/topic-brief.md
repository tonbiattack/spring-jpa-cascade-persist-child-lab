# 題材企画: 親保存で新規子をカスケード永続化しない

`Catalog`へ新規`CatalogItem`を追加して保存するとき、子も永続化され、再読込した親から一件取得できなければならない。バグ状態では`@OneToMany`に`CascadeType.PERSIST`がなく、flush時に未永続の子を参照して失敗する。

Jakarta Persistenceの`CascadeType.PERSIST`は永続化操作を関連エンティティへ伝播する。[1] 既存orphanRemoval題材は既存子の削除を扱うのに対し、本題材は新規子の永続化を扱う。

[1]: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/cascadetype "Jakarta Persistence API: CascadeType"
