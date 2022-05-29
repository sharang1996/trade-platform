
# Position Book

As an investment bank we have the requirement to maintain the total quantity of a traded
security* held at any point in time (this is referred as real time position) 

- All the positions are stored in a Position Book, aggregated using the trading account and the security identifier. The Position Book also stores the details of all the events involving the trading account and security.
- The position book is updated whenever a Trade Event comes into the system; a trade event can represent an operation to buy a security, sell a security or cancel a previously issued event. 
- A trade event always carries a ID and the number of securities to trade (buy or sell). A cancel event is meant to cancel any trade event with the same event ID; as a consequence, the quantity and security Id of a cancellation event are meaningless.
The goal is to develop a Position Book system that can process trade events and from which a
user can retrieve a particular position identified by a trading account and security. The system
should keep the data in memory (not in a database or in the file system)

*A security is a tradable asset of any kind. Examples of securities are debt securities (such as
banknotes, bonds and debentures), equity securities, (e.g., common stocks) and derivative
contracts, such as forwards, futures, options and swaps.

### Assumptions

- A valid security is alphanumeric, and is less than 10 characters long.
- A valid account his alphanumeric, and is always 4 characters long.
- A trade event is always "BUY", "SELL" or "CANCEL".
- A trade id is a positive integer less than 2147483648 (integer limit).
- Input stream is terminated by subsequent new line characters(A single one could mean we have more trade events to follow).

### Usage

- Install java(11), maven
- Run mvn clean install
- Invoke the application by running PositionBookRunner
- Input all the trades to make with fields space seperated, trades line seperated.
