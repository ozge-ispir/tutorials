import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Article} from '../../models/article';
import {ArticleRepository} from '../../services/article.repository';

@Component({
  selector: 'ngu-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {
  book: Observable<Article>;

  constructor(
    private route: ActivatedRoute,
    private bookService: ArticleRepository
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.book = this.bookService.byId(params.get('id'));
    });
  }

}
