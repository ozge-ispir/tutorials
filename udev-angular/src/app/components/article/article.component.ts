import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Article} from '../../models/article';
import {ArticleRepository} from '../../services/article.repository';

@Component({
  selector: 'ngu-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
  article: Observable<Article>;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleRepository
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.article = this.articleService.byId(params.get('id'));
    });
  }

}
