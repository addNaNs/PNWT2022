export class CourseOfferModel {
    public itemId: string;
    public name: string;
    public description: string;
    public imagePath: string;

    constructor(itemId: string, name: string, description: string, imagePath: string){
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }
}