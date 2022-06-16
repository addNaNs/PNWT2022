export class CourseItemModel {

    constructor(public itemId: string, public name: string, public description: string,
        public price: Float32Array, public color: string, public quantity: Float32Array, public imagePath: string) {}
}