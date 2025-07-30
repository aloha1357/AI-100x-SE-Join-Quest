# BDD 開發報告

## 專案結構

```
AI-100x-SE-Join-Quest/
├── build.gradle
├── src/
│   ├── main/
│   │   └── java/com/ai100x/order/
│   │       ├── OrderService.java
│   │       ├── OrderItem.java
│   │       └── OrderResult.java
│   └── test/
│       └── java/com/ai100x/order/
│           └── OrderStepDefinitions.java
│   └── test/resources/order.feature
└── BDD pricing/
    ├── DEV_REPORT.md
    └── order.feature
```

## 已完成驗收情境


### Scenario 1: Single product without promotions
- 功能：無任何優惠，計算單一商品訂單金額
- 測試：已通過


### Scenario 2: Threshold discount applies when subtotal reaches 1000
功能：滿額折扣，訂單金額達到門檻時折抵指定金額
測試：已通過

### Scenario 3: Buy-one-get-one for cosmetics - multiple products
功能：化妝品類商品買一送一，計算訂單金額與收到商品數量
測試：已通過

### Scenario 4: Buy-one-get-one for cosmetics - same product twice
功能：同一化妝品買一送一，2件收到3件，計算訂單金額與收到商品數量
測試：已通過

### Scenario 5: Buy-one-get-one for cosmetics - mixed categories
功能：化妝品買一送一，混合非化妝品（如襪子），僅化妝品享優惠，計算訂單金額與收到商品數量
測試：已通過

### Scenario 6: Multiple promotions stacked
功能：同時套用滿額折扣與化妝品買一送一，計算訂單金額、折扣、收到商品數量
測試：已通過

## 主要程式檔案

 `OrderService.java`：訂單服務，負責計算金額與優惠，已支援一般訂單、滿額折扣、化妝品買一送一、疊加優惠（Scenario 1~6 全部通過）
 `OrderItem.java`：商品項目，已支援 category 欄位
 `OrderResult.java`：訂單結果，包含原始金額、折扣、總金額、收到商品
 `OrderStepDefinitions.java`：Cucumber 步驟定義，已支援 scenario 1~6，步驟定義已合併可動態判斷情境
 `order.feature`：驗收情境，已開啟 scenario 1~6，建議逐步開啟與測試

## 開發流程建議
1. 嚴格遵守 BDD 最小增量原則，一次只開啟一個 scenario。
2. 先撰寫步驟定義（全部丟 PendingException），確認測試失敗原因正確。
3. 再逐步實作程式碼，讓 scenario 通過。
4. 每次通過後再開啟下一個 scenario。

## 開發注意事項與常見問題

### 測試與檔案路徑注意事項
- **order.feature 路徑需正確**：Cucumber 測試只會執行 `src/test/resources/order.feature`，`BDD pricing/order.feature` 僅供規格參考，請勿混用。
- **測試時請確認 scenario 是否全部開啟**：如有 scenario 未被執行，請檢查 feature 檔案內容與路徑。
- **測試報告**：執行 `./gradlew.bat test` 後，可於 `build/reports/tests/test/index.html` 查看詳細結果。
- **.gitignore**：請確保 build、IDE、OS 相關暫存檔案已加入忽略，避免汙染版本庫。


- 步驟定義（Step Definitions）不可重複，Cucumber 會因重複 annotation 而報錯，請合併或移除重複步驟。
- 測試執行過慢時，請檢查 JUnit/Cucumber timeout 設定（可於 `junit-platform.properties` 設定預設 timeout）。
- 若 scenario 太多，建議只開啟一個 scenario 測試，其他註解，避免 BDD流程失焦。
- DataTable 欄位需動態判斷，步驟定義建議合併，支援多種欄位格式。
- Gradle 測試失敗時，請優先檢查 annotation、步驟定義、feature 檔案格式是否正確。
- 若遇到 build cache 或 daemon 問題，可嘗試清除 Gradle cache 或重啟 IDE。
- 測試報告可於 `build/reports/tests/test/index.html` 查看詳細結果。


## 最終進度與建議
- 所有 scenario（1~6）已全部實作並通過測試。
- 驗收情境請維護於 `src/test/resources/order.feature`，勿使用其他路徑。
- 相關程式請放在 `src/main/java/com/ai100x/order/`。
- 測試步驟請放在 `src/test/java/com/ai100x/order/OrderStepDefinitions.java`。
- 測試報告可於 `build/reports/tests/test/index.html` 查看。


---

如需 ERD/OOD 設計圖，請參考專案根目錄 BDD pricing 下的 `ERD.png`、`OOD.png`。
