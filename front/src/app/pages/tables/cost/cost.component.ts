import { Component, OnInit } from '@angular/core';
import { Cost } from '../../../@core/data/cost';
import { CostService } from '../../../@core/mock/cost.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css']
})
export class CostComponent implements OnInit {

  costs: Cost[] = [];
  currentCost: Cost = new Cost();

  selectedCostId: number | null = null;
  isCreatingNewCost: boolean = false;
  isUpdatingCost: boolean = false;

  constructor(
    private costService: CostService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getCosts();

    this.route.params.subscribe(params => {
      this.selectedCostId = +params['id'];
      if (this.selectedCostId) {
        this.getCostDetails(this.selectedCostId);
        this.isCreatingNewCost = false;
        this.isUpdatingCost = false;
      } else {
        this.isCreatingNewCost = true;
        this.isUpdatingCost = false;
      }
    });
  }

  getCosts(): void {
    this.costService.getCostsList().subscribe(data => {
      this.costs = data;
    });
  }

  getCostDetails(id: number): void {
    this.costService.getCostById(id).subscribe(data => {
      this.currentCost = data;
    });
  }

  createCost(cost: Cost): void {
    this.costService.createCost(cost).subscribe(() => {
      this.getCosts();
    });
  }

  updateCost(cost: Cost): void {
    this.costService.updateCost(cost.id, cost).subscribe(() => {
      this.getCosts();
      this.router.navigate(['/cost-management']);
    });
  }

  deleteCost(id: number): void {
    this.costService.deleteCost(id).subscribe(() => {
      this.getCosts();
      this.router.navigate(['/cost-management']);
    });
  }

  navigateToCreateCost(): void {
    this.router.navigate(['/cost-management/new']);
  }

  navigateToUpdateCost(id: number): void {
    this.router.navigate(['/cost-management/update', id]);
  }
  onSubmit(): void {
  if (this.isCreatingNewCost) {
    this.createCost(this.currentCost);
  } else if (this.isUpdatingCost) {
    this.updateCost(this.currentCost);
  }
}

  
}
