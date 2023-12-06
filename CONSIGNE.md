DAI lab: SMTP
=============

Objectives
----------

In this lab, you will develop a TCP client application in Java. This client application will use the Socket API to communicate with an SMTP server. The code that you write will include a **partial implementation of the SMTP protocol**. 

These are the objectives of the lab:

* Implement a more complex TCP client application in Java, which uses the Socket API to communicate with an SMTP server.
* Make practical experiments to become familiar with the **SMTP protocol**. After the lab, you should be able to use a command line tool to communicate with an SMTP server. You should be able to send well-formed messages to the server, in order to send emails to the address of your choice.
* Design a simple object-oriented model to implement the functional requirements described in the next paragraph.


Functional requirements
-----------------------

Your mission is to develop a client application that automatically plays e-mail pranks on a list of victims:

* As configuration, the user of your program should provide
  1. the **victims list**: a file with a list of e-mail addresses,
  2. the **messages list**: a file with several e-mail messages (subject and body),
  3. the **number of groups** `n` on which the e-mail prank is played. This can be provided e.g., as a command line argument.
* Your program should form `n` groups by selecting 2-5 e-mail addresses from the file for each group. The first address of the group is the sender, the others are the receivers (victims).
* For each group, your program should select one of the e-mail messages. 
* The respective messages are then sent to the diffent groups using the SMTP protocol.

Constraints
-----------

* Your client must be implemented in Java, with the `java.io` API.
* The goal is for you to work at the wire protocol level (with the Socket API). Therefore, you CANNOT use a library that takes care of the protocol details. You have to work with the input and output streams.
* The program must be configurable: the addresses, groups, messages CANNOT be hard-coded in the program and MUST be managed in config files.
* You must send **one** e-mail per group, and not one e-mail for every member of every group.
* There must be at least a simple validation process of the input files that displays errors on the console to describe what's wrong (e.g. an invalid number of groups, an invalid e-mail address that does not contain a '@' character, an invalid format, etc.).
* The subject and body of the messages may contain non-ASCII characters. You have to encode the body and the subject of the e-mail correctly. You can find more information [here](https://ncona.com/2011/06/using-utf-8-characters-on-an-e-mail-subject/).


Example
-------

Consider that your program generates a group G1. The group sender is Bob. The group recipients are Alice, Claire and Peter. When the prank is played on group G1, then your program should pick one of the fake messages. It should communicate with an SMTP server, so that Alice, Claire and Peter receive an e-mail, which appears to be sent by Bob.

SMTP server
-----------

You can use [MailDev](https://github.com/maildev/maildev) as a mock SMTP server for your tests.  **Do not use a real SMTP server**.

Use docker to start the server:

    docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev

This provides a Web interface on localhost:1080 and a SMTP server on localhost:1025.

Deliverables
------------

You will deliver the results of your lab in a GitHub repository. You do not have to fork a specific repo, you can create one from scratch.

Your repository should contain both the source code of your Java project and your report. Your report should be a single `README.md` file, located at the root of your repository. The images should be placed in a `figures` directory.

Your report MUST include the following sections:

* **A brief description of your project**: if people exploring GitHub find your repo, without a prior knowledge of the API course, they should be able to understand what your repo is all about and whether they should look at it more closely.

* **Instructions for setting up your mock SMTP server**. The user who wants to experiment with your tool but does not really want to send pranks immediately should be able to use a mock SMTP server.

* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.

* **A description of your implementation**: document the key aspects of your code. It is a good idea to start with a **class diagram**. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).

References
----------

* The [SMTP RFC](<https://tools.ietf.org/html/rfc5321#appendix-D>), and in particular the [example scenario](<https://tools.ietf.org/html/rfc5321#appendix-D>)
* The [mailtrap](<https://mailtrap.io/>) online service for testing SMTP
