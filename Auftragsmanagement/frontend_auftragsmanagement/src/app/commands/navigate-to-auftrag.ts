import { Router } from "@angular/router";
import { Command } from "./command";


export class NavigateToAuftragCommand implements Command {

constructor(private router: Router) {}

execute(): void {
    this.router.navigate(['/auftrag']);
}

}
