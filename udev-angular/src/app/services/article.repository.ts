import {Observable} from 'rxjs';
import {Article} from '../models/article';

export abstract class ArticleRepository {
  abstract add(article: Article): Observable<Article>;

  abstract all(): Observable<Article[]>;

  abstract byId(id: string): Observable<Article>;
}
