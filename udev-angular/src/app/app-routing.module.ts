import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ArticleComponent} from './components/article/article.component';
import {LibraryComponent} from './components/library/library.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import {ListeUserComponent} from './components/liste-user/liste-user.component';

const routes: Routes = [
  {path: 'library', component: LibraryComponent},
  {path: 'articles/:id', component: ArticleComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'liste-user', component: ListeUserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
