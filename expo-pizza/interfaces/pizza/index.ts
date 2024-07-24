import { ICategory } from '@/interfaces/category'
import { IPhoto } from '@/interfaces'

export interface IPizza {
  id: number
  name: string
  description: string
  rating: number
  isAvailable: boolean
  category: ICategory
  photos: IPhoto[]
  // ingredients: IIngredient[]
  // sizes: IPizzaSize[]
  // dateCreated: string
}
