package banking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// This class represents one transaction — either a deposit or withdrawal.
// Once created, the data inside cannot be changed.
public class Transaction {

    // An enum is a fixed list of allowed values.
    // Type can only ever be DEPOSIT or WITHDRAWAL — nothing else.
    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }

    // This controls how the date and time is displayed when we print a transaction.
    // Example output: 2024-03-15 14:32
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // These are the four pieces of information every transaction stores.
    // 'final' means they can only be set once and never changed after that.
    private final Type type;           // DEPOSIT or WITHDRAWAL
    private final double amount;       // how much money
    private final double balanceAfter; // account balance after this transaction
    private final LocalDateTime timestamp; // date and time of the transaction

    // Constructor — called when we create a new Transaction object.
    // It takes 3 values. The timestamp is set automatically to right now.
    public Transaction(Type type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now(); // records the current date and time
    }

    // Getters — these let other classes read the values but not change them.
    // There are no setters, so the transaction stays locked after creation.

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // toString() controls what prints when you do System.out.println(transaction).
    // %.2f means: print a decimal number with exactly 2 decimal places.
    // Example output: [2024-03-15 14:32] DEPOSIT R 500.00 Balance: R 1500.00
    @Override
    public String toString() {
        return String.format("[%s] %s R %.2f Balance: R %.2f",
                timestamp.format(FORMATTER),
                type,
                amount,
                balanceAfter);
    }
}
