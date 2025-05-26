import { Routes } from '@angular/router';
import { TEst2Component } from './components/test2/test2.component';
import { LoginpageComponent } from './components/loginpage/loginpage.component';
import { ArtikelListeComponent } from './components/artikel/artikel-liste/artikel-liste.component';
import { AuftragListeComponent } from './components/auftrag/auftrag-liste/auftrag-liste.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';
import { KundeListeComponent } from './components/kunde/kunde-liste/kunde-liste.component';
import { ArtikelComponent } from './components/artikel/artikel.component';
import { KundeComponent } from './components/kunde/kunde.component';
import { AuftragDetailComponent } from './components/auftrag/auftrag-detail/auftrag-detail.component';
import { AuftragComponent } from './components/auftrag/auftrag-formular.component';
import { RoleGuard } from './role.guard';

export const routes: Routes = [


    { path: '', redirectTo: '/loginpage', pathMatch: 'full' },
    { path: 'test2', component: TEst2Component },
    { path: 'loginpage', component: LoginpageComponent },
    { path: 'artikel', component: ArtikelListeComponent },
    { path: 'artikel/:id', component: ArtikelComponent },
    { path: 'auftrag', component: AuftragListeComponent },
    { path: 'landingpage', component: LandingpageComponent },
    { path: 'auftragdetail/:id', component: AuftragDetailComponent },
    { path: 'auftragformular', component: AuftragComponent },

      {
        path: 'kunde',
        component: KundeListeComponent,
        canActivate: [RoleGuard],
        data: { roles: ['admin', 'editor'] }
    },
    {
        path: 'kunde/:id',
        component: KundeComponent,
        canActivate: [RoleGuard],
        data: { roles: ['admin', 'editor'] }
    },
];

