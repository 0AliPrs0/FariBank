# 🏦 FariBank - A Lovely Neo Bank

This project is a complete simulation of a modern neo bank called "FariBank". The project was developed in two main phases; the first phase focused on implementing basic features, and the second phase expanded the system's capabilities by adding new administrative roles and connecting to other banking networks.

---

## 📖 Table of Contents

- [📝 About the Project](#about-the-project)
- [👥 System Roles](#system-roles)
  - [👤 Regular User (Customer)](#regular-user)
  - [🛡 Support Staff](#support-staff)
  - [👑 System Administrator](#system-administrator)

---

## 📝 About the Project

FariBank is a fully virtual and online bank that provides all its services without needing a physical branch. The main goal of this project is to build a comprehensive banking platform with key features such as account management, secure money transfers, investment options, and 24/7 support.

In the final version, the main focus has been on improving and refactoring previous code, adding the necessary structures for easier future maintenance, and expanding features to create a competitive product in the market.

---

## 👥 System Roles

The system has three main roles with different access levels:

### Regular User
Customers are the beating heart of the bank and have access to a full range of financial services:

- 🔑 Sign-up and Login: Users sign up in the system with their mobile number.
  > Note: The password must be strong and include a combination of uppercase and lowercase letters, numbers, and special characters.

- 🛡 Identity Verification: For complete security, all account features remain disabled until identity is verified by the support team.

- 💰 Account Management: Includes instant account top-up, viewing the current balance, and receiving a complete list of transactions with the ability to sort by date and filter by a time range.

- 📞 Contact Management: Users can add, edit, and manage their contact list for faster money transfers.

- 📈 Investment Funds:
  - Savings Fund: A virtual account for simple money storage and management.
  - Remainder Fund: A smart way to save small change; after each transaction, the remaining amount is rounded up and deposited into this fund.
  - Reward Fund: A fixed-term investment fund that deposits a fixed monthly interest into the user's main account.

- 📱 SIM Card Top-up: Ability to buy mobile credit for any number, the user's own number, or one of the saved contacts.

- 💸 Money Transfer: Users can transfer money to an account number, card number, or contacts (if the contact is mutual).

  | Method Name | Destination | Transfer Speed | Fee |
  | :--- | :--- | :--- | :--- |
  | Fari-to-Fari | Internal FariBank Accounts | Instant | Free |
  | Card-to-Card| Other Banks | Instant | Fixed |
  | Paya / Pol | Other Banks | Per Banking Cycle | Variable |

- 💬 Support: Ability to submit a support request for different parts of the system and track its status (Submitted, In Progress, Closed).

- ⚙️ Settings: Access to security settings such as changing the password, setting or changing the card PIN (4-digit), and enabling/disabling the contact transfer feature.

### Support Staff
These users are responsible for handling customer issues and managing the initial account security process.

- 👷 Managed by Admin: Support users are created and managed by the System Administrator, who assigns specific tasks to them.
- ✅ Identity Verification: Reviewing and approving or rejecting new user verification requests with a reason.
- 📨 Handling Requests: Managing user support tickets, recording responses, and updating their status.
- 🗂 Task Assignment: Each support user only has access to requests related to their assigned department.
- 📋 Viewing User Information: Limited and controlled access to customer information solely for support purposes.

### System Administrator
This role has the highest level of access in the system and is responsible for the overall platform management.
- 🤴 Centralized Management: A senior administrator is pre-defined in the system who creates and manages other administrators and support users.
- 📊 Main System Settings: Ability to define and change core system parameters like fee rates and fund interest percentages.
- 👤 Full User Management: Viewing the list of all system users and the ability to create, edit, or block user accounts at all levels.
- ⚙️ Batch Transaction Execution: Manually executing pending transactions such as monthly interest payments for funds or settling Paya transfers.
