# whos-that-pokemon-java
Faster universal version of the python discord bot that works for infinite amount of servers, Work in progress.

Since the python version started to lag and had to be tailored to every single server, I wanted to make this universal and faster. The coding is on hold as I have major changes in the architecture to optimize the ability to handle an incredible number of servers at once without lag. The first idea was to have 1 thread per server, but if 100,000 servers use it, the thread queue will cause problems.

The last solution I have come up with is to save the progression in the message the bot sends out so that it can read the previous message it sent in a given channel and find out where in the game it is. This will mean that all threads can solve all server's requests and we will avoid threads queue.
