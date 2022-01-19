# WordCloud

## Advanced Object Oriented Software Development
    
##       Aiden Desmond
##    <G00398273@gmit.ie> 

### End of Semester Design Project

### Design Brief Overview:

> You are required to develop a Java application that can parse a file or a URL to generate a
> PNG (portable network graphics) file with a word-cloud displaying the most prominent words
> in decreasing font size, style and colour

The enclosed wcloud.jar meets this requirement by providing the user with a command line interface which permits the user to:

* Select a file or an URL to parse
* Specify the Number of Words to Display
* Specify the filename for the resulting WordCloud.

The WordCloud presents as a png graphic, with the words displayed in differing font sizes and colours.

### Design Concept

The idea of parsing a body of text to present a word cloud is a well-known task in computing design. Methods exist to achieve the result using a variety of simple tools, such as some combination of `awk`, `sort` and `uniq`. These can be quite slow, however, so the task required research into the fastest methods of creating a Table of Words, the fastest way to sort them, filter them against a known set of `bad` words, and restrict the contents of the Table to the users required density.

I decided to use much of my OOSD design project for the menu system, as this permitted me to concentrate on the rest of the design work. Once the user has
complied with instructions, the menu hands off the totality of decisions to a Creator Utility Class, which creates the Word Cloud using abstracted interfaces.

### Parse Interface

This abstract interface is designed to read a resource and return a HashMap of word frequency from the file. 'Bad' words are obtained from a separate interface, and provided as a HashSet for the quickest possible comparison and removal of words.

### Sorter Interface

While it may be possible to extract the information from the HashMap more quickly, I found it was more impactful to run a Collections.Sort function on the contents of the HashMap. The interface does not require the use of a Collections.Sort, but the implementation makes use of a Comparator class for this purpose.

### Shortener Interface

The design requires that the user be capable of reducing the size of the cloud to a preferred limit. Shortener accomplishes this through the use of an intermediate iterator, limited to the integer value selected.

### CalcMap

While acceptable standards were accomplishable using the raw values from the Sorter, I found a better result could be achieved using a special calculation, where each words' frequency is calculated as a percentage of the total word count of the selected words. This is accomplished through an interface permitting adjustment to the Values of the frequency

### CloudWord

Finally, the sorted, reduced, adjusted HashMap is drawn as a wordcloud.  Colours are assigned randomly, as the size adjustment which can be achieved with the use of the percentages makes word prominence very clear.

I read the chapter of the Design book referred to in the labs, and, while I was unable to get the word-boundaries to be settled in the same manner as described in that book (as I could not get an acceptable outcome from the intersect() method of GlyphVector.outline() boundaries) it did lead me to the world of `awt.FontMetrics` and `awt.geom.Rectangle2D`.

Using another HashSet, called lava, to store the bounds of words already painted, allowed me to achieve a pleasing word-avoidance in the final drawing of the image as shown in the example.png.

There is one method noted from a website in the code, all other information came from the JavaDocs for the various classes used and from Oracle's java tutorials, some of which are out of date, but all of which are an excellent resource.
