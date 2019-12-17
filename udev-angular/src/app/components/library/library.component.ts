import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Article} from '../../models/article';
import {ArticleRepository} from '../../services/article.repository';

@Component({
  selector: 'ngu-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.scss']
})
export class LibraryComponent implements OnInit {
  library: Observable<Article[]>;

  constructor(
    private bookService: ArticleRepository
  ) { }

  ngOnInit() {
    this.library = this.bookService.all();
  }

}
