# Machine Learning using Naïve Bayes

## OOP Assignment by Matthew Ennis
###[Demo video link](https://youtu.be/FN74Hi4hB4Q)
> In your own words, write a short (< 200 words) explanation of how your application works

My application allows the user to select a file containing data about documented COVID-19 tests and use this information to predict the COVID status of another patient given their symptoms. It does this using the Naïve Bayes classifier which is a relatively simple (by machine learning standards) method of calculating the probability of a result by getting the probability of each feature given a positive and negative result. The application reads in a set of entries and then the frequency of each feature is stored in a hashmap which is later used in the calculation. The application can also test it's own accuracy by attempting to predict the results for a cases in a file and checking how many it gets right. All of these tasks can be performed through the GUI with no commandline interaction needed.

> List the classes you created, with a description

**Control** contains the main which simply creates the GUI and nothing else

**Entry** is used to store each case, containing the 6 features: temperature, aches, cough, sore throat, recently in danger zone, and COVID-19. There are multiple constructors, two for regular input (one with COVID and one without) as well as one that takes an array of strings which is the easiest way to get it from the csv. This class has no methods other than getters, setters, and toString.

**NaiveBayes** is where all the actual machine learning happens. It uses the default constructor as it doesn't necessarily do anything at first. Its readFile method will interpret the contents of a csv and store them in an array of Entry objects. It can then use generateFrequency to analyse the contents of said array and store the frequencies of each feature given positive or negative COVID-19 and store them in a collection of hashmaps. The predict method will take an Entry with or without the COVID-19 attribute and predict whether or not it has it using the Naïve Bayes classifier. It essentially multiplies the probability of each feature given positive COVID, then does the same for negative. It then calculates P(yes)/P(yes)+P(no). It returns this value multiplied by 100 so it'll be in percentage form. The testAccuracy method will read through an Entry array provided by readFile and call the predict method for each one, then return what percentage of them it was correct about.

**GUI** is the graphical user interface class, it instantiates a NaiveBayes object which the user can then easily use all the methods of. There's really not much more I can say about a GUI, it's more of a visual thing.
