import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatSnackBar} from '@angular/material';
import {Article} from '../../models/article';
import {ArticleRepository} from '../../services/article.repository';

@Component({
  selector: 'ngu-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  articleForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private articleService: ArticleRepository
  ) {
    this.articleForm = this.formBuilder.group({
      title: '',
      message: '',
      author: ''
    });
  }

  ngOnInit() {
  }

  onSubmit(data: Article) {
    if (this.isFormComplete(data)) {
      this.articleService.add(data)
        .subscribe(() => {
          this.articleForm.reset();
          this.openSnackBar('Le sujet a été ajouté');
        });
    }
  }

  private isFormComplete(data: Article) {
    return data && (data.title && data.message && data.author);
  }

  private openSnackBar(message: string) {
    this.snackBar.open(message, 'Super!', {
      duration: 5000,
    });
  }

}
