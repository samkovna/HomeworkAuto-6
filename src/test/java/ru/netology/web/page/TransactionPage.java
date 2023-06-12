package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionPage {
    private SelenideElement heading = $x("//h1[contains(text(), 'Пополнение')]");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromCard = $("[data-test-id='from'] input");
    private SelenideElement transactionButton = $("[data-test-id='action-transfer']");

    public TransactionPage() {
        heading.shouldBe(visible);
    }

    public void getTransaction(String amountToTransaction, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransaction);
        fromCard.setValue(cardInfo.getNumber());
        transactionButton.click();
    }

}
