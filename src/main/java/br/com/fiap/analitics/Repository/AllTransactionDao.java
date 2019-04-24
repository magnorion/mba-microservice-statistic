package br.com.fiap.analitics.Repository;

import br.com.fiap.analitics.Domain.AllTransaction;
import br.com.fiap.analitics.Domain.NewTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllTransactionDao {
    public AllTransaction resume(ArrayList<NewTransaction> transactionArray) {
        long actual = System.currentTimeMillis();

        AllTransaction allTransaction = new AllTransaction();
        List<NewTransaction> result = transactionArray.stream()
                .filter(a -> (actual - a.getData())  <= 60000).collect(Collectors.toList());

        allTransaction.setSum(result.stream()
                .mapToDouble(NewTransaction::getAmount).sum());
        allTransaction.setAvger(result.stream()
                .mapToDouble(NewTransaction::getAmount).average().getAsDouble());
        allTransaction.setMinimum(result.stream()
                .mapToDouble(NewTransaction::getAmount).min().getAsDouble());
        allTransaction.setMaximum(result.stream()
                .mapToDouble(NewTransaction::getAmount).max().getAsDouble());
        allTransaction.setCounter(result.size());

        return allTransaction;
    }
}
