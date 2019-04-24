package br.com.fiap.analitics.Repository;

import br.com.fiap.analitics.Domain.NewTransaction;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NewTransactionDao {
    private ArrayList<NewTransaction> transactionArray;

    public NewTransactionDao() {
        this.transactionArray = new ArrayList<>();
    }

    public ArrayList<NewTransaction> getList() {
        return this.transactionArray;
    }

    public HttpStatus save(NewTransaction transaction) {
        long actual = System.currentTimeMillis();
        long calc = TimeUnit.MILLISECONDS.toSeconds(actual - transaction.getTimestamp());
        transaction.setData(actual);

        HttpStatus status = HttpStatus.CREATED;
        this.transactionArray.add(transaction);

        if(calc >= 60) {
            status = HttpStatus.NO_CONTENT;
        }

        return status;
    }

}
