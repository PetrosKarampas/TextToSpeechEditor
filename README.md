## Text To Speech Editor
	This is a Text To Speech Editor Project in Java 
## User Stories  
* [US-1] As a user, I want to create a new empty document, by giving its title and author. The
	application should automatically record the creation date.
	
* [US-2] As a user, I want to edit the contents of the document, via the application's user interface.
* [US-3] As a user, I want to save the contents of the document to disk by providing a particular
filename. The application should automatically record the save date.
* [US-4] As a user, I want to open the contents of an existing document from disk by providing a particular
file path, or by browsing the file system folders.
* [US-5] As a user, I want to transform the contents of the document to speech.
* [US-6] As a user, I want to select a line and transform it to speech.
* [US-7] As a user I want to transform the contents of the document to speech in reverse, i.e. play the
last word of the last line first and so on.
* [US-8] As a user I want to select a line and transform it to speech in reverse, i.e. and play the last
word first and so on.
* [US-9] As a user I want to encode the contents of the document and then transform them to speech.
* [US-10] As a user I want to select a line, encode it and transform it to speech.
* [US-11] As a user I want to tune the text encoding technique. In particular the application should
				support at least the following encoding strategies:
				
				◼ Atbash: The Atbash cipher is formed by taking the alphabet and mapping it to its reverse, so that the first letter becomes the last letter, the second letter becomes the second to last letter, and so on.
		
				◼ Rot-13: Rot-13 is a letter substitution cipher that replaces a letter with the 13th letter after it, in the alphabet. Rot-13 is a special case of the Caesar cipher, which was developed in ancient Rome.
* [US-12] As a user I want to be able to tune the audio parameters, i.e., the volume, the speech rate
and the pitch.

## Design
* We are using the Model View Controller Software design pattern
* Design patterns used in this project:
	* Command Pattern
	* Template Method Pattern
	* Parameterized Factory Pattern
	* Strategy Pattern
	* Adapter Pattern
	
## Class Diagram
![enter image description here](https://photos.google.com/photo/AF1QipNLwMGbkGFjLp2xGlGQCsFvZ4Q0Br1qe2dFJZy0)
# TextToSpeechEditor
