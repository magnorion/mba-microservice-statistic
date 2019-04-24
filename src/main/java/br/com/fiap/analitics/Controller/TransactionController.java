package br.com.fiap.analitics.Controller;
import br.com.fiap.analitics.Domain.NewTransaction;
import br.com.fiap.analitics.Repository.AllTransactionDao;
import br.com.fiap.analitics.Repository.NewTransactionDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Transaction", description = "Transactions services")
public class TransactionController {
    private NewTransactionDao daoNew;
    private AllTransactionDao daoAll;

    public TransactionController() {
        this.daoNew = new NewTransactionDao();
        this.daoAll = new AllTransactionDao();
    }

    @PostMapping("/transactions")
    public ResponseEntity save(@RequestBody NewTransaction transaction) {
        return new ResponseEntity(this.daoNew.save(transaction));
    }

    @GetMapping("/statistics")
    public ResponseEntity get() {
        return new ResponseEntity(this.daoAll.resume(this.daoNew.getList()), HttpStatus.OK);
    }
}

