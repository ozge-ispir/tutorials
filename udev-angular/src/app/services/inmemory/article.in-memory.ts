import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {map} from 'rxjs/operators';
import {ArticleRepository} from '../article.repository';
import {Article} from '../../models/article';

const library: Article[] = [
  {id: '1', title: 'Dune', message: 'Test1', author: 'Frank HERBERT'},
  {id: '2', title: 'Hyperion', message: 'Test2', author: 'Dan SIMMONS'},
  {id: '3', title: 'Fondation', message: 'Test3', author: 'Isaac ASIMOV'},
  {id: '4', title: 'La Horde du Contrevent', message: 'Test4', author: 'Alain DAMASIO'},
  {id: '5', title: 'Le Maître du Haut Château', message: 'Test5', author: 'Philip K. DICK'}
];

@Injectable()
export class ArticleInMemory implements ArticleRepository {
  private library$: Subject<Article[]> = new BehaviorSubject(library);

  add(article): Observable<Article> {
    return this.library$
      .pipe(
        map(articles => {
          const id: string = (articles.length + 1).toString();
          const newArticle: Article = {id, ...article};
          articles.push(newArticle);
          return newArticle;
        })
      );
  }

  all(): Observable<Article[]> {
    return this.library$;
  }

  byId(id): Observable<Article> {
    return this.library$
      .pipe(
        map(subjects => subjects.filter(subject => subject.id === id)[0])
      );
  }
}
