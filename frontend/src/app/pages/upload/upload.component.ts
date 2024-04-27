import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Interaction } from 'src/app/models/interaction';
import { BrowserHistory } from 'src/app/models/browserHistory';
import { UploadService } from 'src/app/services/upload/upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit
{
  uploadForm: FormGroup;
  interactions: Interaction[] = [];

  constructor(private formBuilder: FormBuilder, private uploadService: UploadService) { }

  ngOnInit(): void
  {
    this.uploadForm = this.formBuilder.group({
      fileInput: [null, Validators.required]
    });
  }

  onFileSelected(event): void
  {

    // Create a new instance of the FileReader to read the file asynchronously
    const reader = new FileReader();
    // Define a function to be executed when the file is completely loaded
    reader.onload = () =>
    {
      const result = reader.result as string;
      const jsonData: BrowserHistory = JSON.parse(result);
      console.log(jsonData[0]);

      this.interactions = this.toInteractions(jsonData, 100);

      // Do something with jsonData, like sending it to a service or processing it
      console.log(this.interactions);
    };
    // Start reading the file
    reader.readAsText(event.target.files[0]);

  }

  toInteractions(jsonData: BrowserHistory, limit: number): Interaction[]
  {
    // Get the keys of the original object
    let keys = Object.keys(jsonData);

    // Create a new object with the first 100 elements
    let interactions: Interaction[] = [];
    for (let i = 0; i < limit && i < keys.length; i++) {
      interactions.push({
        title: jsonData[i].title,
        url: jsonData[i].url,
        time: new Date(jsonData[i].lastVisitTime)
      });
    }

    return interactions;

  }


  onSubmit(): void
  {
    this.uploadService.uploadHistory(this.interactions);

    // You can now send formData to the server using HttpClient

  }
}
