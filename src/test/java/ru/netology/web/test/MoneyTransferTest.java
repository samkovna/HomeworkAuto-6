package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransactionFromFirstCardToSecondCard() {
        var cardFirstInfo = getFirstCard();
        var cardSecondInfo = getSecondCard();
        var balanceCard1 = dashboardPage.getCardBalance(cardFirstInfo);
        var balanceCard2 = dashboardPage.getCardBalance(cardSecondInfo);
        var amount = 15000;
        var expectedBalanceFirstCard = balanceCard1 - amount;
        var expectedBalanceSecondCard = balanceCard2 + amount;
        var transactionPage = dashboardPage.cardTransaction(cardSecondInfo);
        transactionPage.getTransaction(String.valueOf(amount), cardFirstInfo);
        var actualBalanceCard1 = dashboardPage.getCardBalance(cardFirstInfo);
        var actualBalanceCard2 = dashboardPage.getCardBalance(cardSecondInfo);

        assertEquals(expectedBalanceFirstCard, actualBalanceCard1);
        assertEquals(expectedBalanceSecondCard, actualBalanceCard2);

    }
}

