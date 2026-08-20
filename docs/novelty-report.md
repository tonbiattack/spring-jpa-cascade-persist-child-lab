# 新規性レポート: 親保存で新規子をカスケード永続化しない

既存のorphanRemoval教材は親から外した既存子の削除を扱います。本ラボは新規子を親へ追加して保存するときのPERSIST伝播を扱います。

| 軸 | 本ラボ | orphanRemoval題材 |
| --- | --- | --- |
| 原因 | `CascadeType.PERSIST`未指定 | 子削除の伝播設定 |
| 境界 | 新規親子の保存と再読込 | 既存子の削除 |
| 契約 | 新規子が一件保存される | 外した子行が削除される |
| 修正 | PERSISTだけを追加 | orphanRemovalを適切に扱う |

Qiita原稿で`CascadeType.PERSIST`と`TransientObjectException`を検索し、同じ契約の原稿は確認されませんでした。Repository Catalogは存在しないため、Qiita原稿と先行教材による代替監査であることを明記します。

## References

[1]: https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/cascadetype "Jakarta Persistence API: CascadeType"
