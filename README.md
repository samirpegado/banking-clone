# Bank Application with Kotlin and Jetpack Compose

![](https://github.com/samirpegado/banking-clone/blob/main/bankingclone.gif)

This is a bank application project developed using the Kotlin language and the Jetpack Compose library. The main objective of this project was to apply fundamental concepts of Jetpack Compose, such as NavController, ViewModel, List, Grids, and LazyColumns, providing a rich and modern user experience.

## Implemented Features

In this project, various features provided by Jetpack Compose were explored:

- **NavController**: NavController was used to manage navigation between different screens of the bank application. This enables smooth transitions between different parts of the application, such as the login screen, user profile screen, and other functionalities.

- **ViewModel**: The ViewModel architecture was employed to keep application data separate from the user interface, allowing better code organization and state management.

- **List**: The List function was utilized to display a list of recent transactions in the user's account. This allows the user to easily view their most recent financial activities.

- **Grids**: The grids functionality of Jetpack Compose was used to display key features of the application in an organized grid, providing an intuitive and efficient interface.

- **LazyColumns**: With LazyColumns, we implemented efficient vertical scrolling to display detailed information for each transaction, saving resources and ensuring improved performance.

## Local Data Storage

To make the user experience more personalized and persistent, we implemented local data storage using the SharedPreferences library in Android. This allows the application to retain important information, such as user preferences and bank account data, even after the app is closed.

## How to Run the Project

To run the project on your local machine, follow the steps below:

1. Install Android Studio, if you don't already have it.
2. Download the sample or clone this repository (git clone https://github.com/samirpegado/banking-clone.git)
3. Import the sample into Android Studio.
4. Build and run the sample.