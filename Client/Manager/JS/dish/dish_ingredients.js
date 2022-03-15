export class Dish_Ingredients {

    constructor(params) {
        this.ingID = params.ingID;
        this.type = params.type;
        this.minimum = params.minimum;
        this.defaultv = params.defaultv;
        this.maximum = params.maximum;
        this.itemcode = params.itemcode;
        this.name = params.name;
        this.image = params.image;
        this.carbpg = params.carbpg;
        this.calpg = params.calpg;
        this.proteinpg = params.proteinpg;
    }

    printingredients() {

        var ing = $(document.createElement('div')).attr('id', this.ingID).html(`
                            <div class="ingredient m-1 flex-space-between mb-4">
                                <img class="round-1 icon1-3"
                                    src="${this.image}"
                                    alt="Hot air balloons" />
                                <p>${this.defaultv} unit</p>
                                <p class="ml-1">${this.name}</p>
                            </div>`);

        return ing;
    }


    printingredients2(test) {
        console.log(this.ingID)
        // console.log("----------------")
        // console.log(JSON.stringify(test.filter(i => i.ingID == this.ingID))==JSON.stringify([]))
        // console.log("----------------")
        let updatedIngredient = test.filter(i => i.ingID == this.ingID)

        if (JSON.stringify(updatedIngredient) == JSON.stringify([])) {
            // var ing = $(document.createElement('div')).attr('id', this.ingID).attr("style", "filter: sepia(1)").html(`
            //                 <div class="ingredient m-1 flex-space-between mb-4">
            //                     <img class="round-1 icon1-3"
            //                         src="${this.image}"
            //                         alt="Hot air balloons" />
            //                     <p>${this.defaultv} unit</p>
            //                     <p class="ml-1">${this.name}</p>
            //                 </div>`);
            var ing = $(document.createElement('div')).attr('id', this.ingID).attr("style", "filter: grayscale(100%) brightness(40%) sepia(100%) hue-rotate(-50deg) saturate(600%) contrast(0.8);").html(`
                            <div class="ingredient m-1 mb-4">
                                <strike class="flex-space-between">
                                    <img class="round-1 icon1-3"
                                        src="${this.image}"
                                        alt="Hot air balloons" />
                                    <p>${this.defaultv} unit</p>
                                    <p class="ml-1">${this.name}</p>
                                </strike>
                            </div>`);
        } else {
            if (updatedIngredient[0].defaultv != this.defaultv) {
                if (this.defaultv < updatedIngredient[0].defaultv) {
                    this.defaultv = this.defaultv + " + " + (updatedIngredient[0].defaultv - this.defaultv)
                } else {
                    this.defaultv = this.defaultv + " - " + (parseInt(this.defaultv) - parseInt(updatedIngredient[0].defaultv))
                }
            }
            console.log()
            var ing = $(document.createElement('div')).attr('id', this.ingID).html(`
            <div class="ingredient m-1 flex-space-between mb-4">
                <img class="round-1 icon1-3"
                    src="${this.image}"
                    alt="Hot air balloons" />
                <p>${this.defaultv} unit</p>
                <p class="ml-1">${this.name}</p>
            </div>`);
        }

        return ing;
    }


    printingredientsUpdated(test) {
        console.log(this.ingID)
        // console.log("----------------")
        // console.log(JSON.stringify(test.filter(i => i.ingID == this.ingID))==JSON.stringify([]))
        // console.log("----------------")
        let updatedIngredient = test.filter(i => i.ingID == this.ingID)
        if (JSON.stringify(updatedIngredient) == JSON.stringify([])) {

            // var ing = $(document.createElement('div')).attr('id', this.ingID).attr("style", "box-shadow: 1px 1px 10px #d0b3b3;").html(`
            var ing = $(document.createElement('div')).attr('id', this.ingID).attr("style", "box-shadow: 1px 1px 10px #388e3c;color: #388e3c;").html(`
                            <div class="ingredient m-1 flex-space-between mb-4">
                                <img class="round-1 icon1-3"
                                    src="${this.image}"
                                    alt="Hot air balloons" />
                                <p>${this.defaultv} unit</p>
                                <p class="ml-1">${this.name}</p>
                            </div>`);
        }

        return ing;
    }
}