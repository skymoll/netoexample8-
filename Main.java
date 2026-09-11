public abstract class Account {
    protected long balance;

    public Account(long balance) {
        this.balance = balance;
    }

    public abstract boolean add(long amount);

    public abstract boolean pay(long amount);

    public boolean transfer(Account account, long amount) {
        if (this.pay(amount)) {
            if (account.add(amount)) {
                return true;
            } else {
                this.add(amount);
            }
        }
        return false;
    }

    public long getBalance() {
        return balance;
    }
}

class SimpleAccount extends Account {

    public SimpleAccount(long balance) {
        super(balance);
    }

    @Override
    public boolean add(long amount) {
        if (amount < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (amount < 0 || balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

class CreditAccount extends Account {
    private final long creditLimit;

    public CreditAccount(long balance, long creditLimit) {
        super(balance > 0 ? 0 : balance);
        this.creditLimit = creditLimit < 0 ? creditLimit : -creditLimit;
    }

    @Override
    public boolean add(long amount) {
        if (amount < 0 || balance + amount > 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    @Override
    public boolean pay(long amount) {
        if (amount < 0 || balance - amount < creditLimit) {
            return false;
        }
        balance -= amount;
        return true;
    }
}