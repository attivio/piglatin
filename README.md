# piglatin

## Overview
This is a sample ingestion transformer built using the [Attivio Java SDK](https://github.com/attivio/sdk) which will do something odd but fun: make some content searchable in [Pig Latin](http://en.wikipedia.org/wiki/Pig_Latin). It does this by stacking additional tokens.


## System Requirements
* Same as Attivio installation

## Build and Installation
See https://github.com/attivio/sdk/blob/5.5/attivio_module_sdk.md for instructions on building and installing modules created using the Attivio SDK.

Once the module has been added to your Attivio installation, execute the following steps to create an instance of it and add it to a workflow.

1. Click **System Management > Palette**
2. Click **New**
3. Type **TranslateToPigLatin** in the search box and click **Filter**
4. Expand **Document Transformers** and click the **TranslateToPigLatin** component type that is found and click **OK**
5.In the popup that appears enter the following:

| Field	| Value |
| --- | --- |
| Name	| translateToPigLatin |
| Input Fields | title |
| | text |

6. Click **Save**
7. Click **System Management > Workflows > Ingest**
8. Click **ingestPostProcess**
9. Click the **Add Existing Component** Button
10. Type **translateToPigLatin** in the search box and click **Filter**
11. Expand **Platform Components > Document** and click on the **translateToPigLatin** component and click **OK**
12. Select the **translateToPigLatin** component and click the **Move Up** button to move it before the logDocument component
13. Click **Save**

## Configuration
The **Input Fields** property can be configured to list each of the fields you wish to make searchable in Pig Latin. By default, this will include the `title` and `text` fields.

## Usage Example
This transformer is utilized in the [Attivio Java SDK Quick Start](https://answers.attivio.com/display/extranet55/Attivio+Java+SDK+Quick+Start) as an example of creating custom ingestion transformers in your Attivio projects.

Once content in ingested with this transformer in place, a search for "ainSpay" will return all the documents which contain "Spain" in either the title or text fields and "Spain" will also be highlighted.

## Release History
* 0.1.0-SNAPSHOT - Initial release

## Author
Anthony Paquette
Director, Quality Assurance and Customer Enablement
Attivio, Inc.
